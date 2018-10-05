package movieProcessor;
import java.util.*;

import java.awt.*;
import java.awt.event.*;




import com.sun.media.util.JMFI18N;

import jmapps.export.ExportWizard;
import jmapps.ui.*;
@SuppressWarnings("serial")
public class Export extends JMFrame implements Runnable {
	private ExportWizard    dlgExport = null;
	@SuppressWarnings("rawtypes")
	private Vector          vectorWindows = new Vector ();
	public Export ( String strSourceUrl ) {
		super ( JMFI18N.getResource("jmstudio.export.title") );
		dlgExport = new ExportWizard ( this, strSourceUrl, null );
		dlgExport.addWindowListener ( this );
		dlgExport.setVisible ( true );
	}

	public static void main ( String [] args ) {
		@SuppressWarnings("unused")
		Export          frameExport;
		String          strSourceUrl;
		if ( args.length < 1)
			strSourceUrl = "";
		else
			strSourceUrl = args[0];
		frameExport = new Export ( strSourceUrl );
	}

	public void windowClosed ( WindowEvent event ) {
		int i;
		int nCount;
		Object objWindow;
		Window window;
		window = event.getWindow ();
		if ( window == dlgExport ) {
			vectorWindows = dlgExport.getWindowsLeft ();
			nCount = vectorWindows.size ();
			for ( i = 0;  i < nCount;  i++ ) {
				objWindow = vectorWindows.elementAt ( i );
				if ( !(objWindow instanceof Window) )
					continue;
				if ( !((Window)objWindow).isShowing() )
					continue;
				((Window)objWindow).addWindowListener ( this );
			}
		}
		else if ( vectorWindows.contains(window) ) {
			vectorWindows.removeElement ( window );
		}
		/*
        if ( vectorWindows.isEmpty() )
            System.exit ( 0 );
        }*/
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
	}

}


