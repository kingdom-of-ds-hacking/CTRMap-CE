package rtldr;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xstandard.util.collections.IntList;

public class JExtensionManager<J extends JExtensionReceiver<R>, R extends RExtensionBase<J>> {

	final J iface;
	final String rmoClassName;

	private final List<R> loadedExtensions = new ArrayList<>();
	private final IntList loadedExtensionHashes = new IntList();
	
	private Map<Integer, String> hashLUTDebug = new HashMap<>();

	private JExtensionStateListener extensionStateListener;

	public JExtensionManager(J iface, String rmoClassName) {
		this.iface = iface;
		this.rmoClassName = rmoClassName;
	}

	public Iterable<R> getLoadedExtensions() {
		return loadedExtensions;
	}

	public void setExtensionStateListener(JExtensionStateListener l) {
		this.extensionStateListener = l;
	}

	private static int makeClassHash(ClassLoader cldr, String className) {
		try (InputStream in = new BufferedInputStream(cldr.getResourceAsStream(className.replace('.', '/') + ".class"))) {
			int hash = 7;
			while (in.available() > 0) {
				hash = 37 * hash + in.read();
			}
			return hash;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	void bootstrapR(R riface) {
		if (!loadedExtensions.contains(riface)) {
			int hash = makeClassHash(riface.getClass().getClassLoader(), riface.getClass().getName());
			if (!loadedExtensionHashes.contains(hash)) {
				riface.attach(iface);
				loadedExtensions.add(riface);
				loadedExtensionHashes.add(hash);
				if (extensionStateListener != null) {
					extensionStateListener.onExtensionLoaded(riface);
				}
				hashLUTDebug.put(hash, riface + " @ " + riface.getClass().getClassLoader());
			}
			else {
				System.out.println("Extension with matching bytecode already loaded, skipping... (overriden by " + hashLUTDebug.get(hash) + ")");
			}
		}
	}

	void terminateR(R riface) {
		if (loadedExtensions.contains(riface)) {
			int hash = makeClassHash(riface.getClass().getClassLoader(), riface.getClass().getName());
			if (loadedExtensionHashes.contains(hash)) {
				loadedExtensionHashes.removeValue(hash);
			}
			loadedExtensions.remove(riface);
			if (extensionStateListener != null) {
				extensionStateListener.onExtensionUnloaded(riface);
			}
		}
	}
}
