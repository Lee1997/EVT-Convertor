package evtconvertor.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;

import evtconvertor.parcel.Parser;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	
	private void initialisation() {
		File currentOpenFile = getCurrentFile();
		ArrayList<String> evtCode = getEVTCode(currentOpenFile);
		
		//Removes the comments and blank lines from the strings as they are not needed for the internal strings
		evtCode = cleanCode(evtCode);
		
		//Create a new parser object
		Parser parser = new Parser(evtCode);
		parser.init();
		
		for(int i = 0; i < parser.getSpecs().size(); i++) {
			System.out.println(parser.getSpecs().get(i).toString());
		}
		
	}
	
	private ArrayList<String> cleanCode(ArrayList<String> evtCode){
		for(int i = 0; i < evtCode.size(); i++) {
			if(evtCode.get(i).indexOf("--") != -1) {
				evtCode.set(i, evtCode.get(i).substring(0, evtCode.get(i).indexOf("--")));
			}
			if("".equals(evtCode.get(i))) {
				evtCode.remove(i);
			}
			evtCode.set(i, evtCode.get(i).trim());
		}
		return evtCode;
	}
	
	private ArrayList<String> getEVTCode(File currentOpenFile){
		Scanner sc = null;
		try {
			sc = new Scanner(currentOpenFile, "UTF-8");
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		
		ArrayList<String> lines = new ArrayList<String>();

		while (sc.hasNextLine()) {
		  lines.add(sc.nextLine().trim());
		}
		sc.close();
		return lines;
	}
	
	private File getCurrentFile() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IEditorPart editor = page.getActiveEditor();
		IEditorInput input = editor.getEditorInput();
		IPath path = ((FileEditorInput)input).getPath();
		
		String folderLocation = path.toString().substring(0, path.toString().length() - input.getName().toString().length());
		String fileExtension = input.getName().split(".*\\.")[1];
		
		String fileName = input.getName();
		
		
		return new File(folderLocation + fileName);
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		initialisation(); //Start the code
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"EVTConvertor",
				"Converted!");
		return null;
	}

	
}
