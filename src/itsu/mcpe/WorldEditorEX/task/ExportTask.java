package itsu.mcpe.WorldEditorEX.task;

import java.io.File;

import cn.nukkit.scheduler.FileWriteTask;

public class ExportTask extends FileWriteTask {

	public ExportTask(File file, byte[] contents) {
		super(file, contents);
	}

}
