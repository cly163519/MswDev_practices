import java.io.File;

public class FileTest {
	String s;
	
	
	//需求1： 计算一个目录所有文件大小（递归求和）
	public long fileSize(File dir) {
		long size = 0;
		File[] files = dir.listFiles();
		if(files != null) {
			for(File f : files) {//依次把数组或集合里的每一个元素，取出来放到变量 f 里，然后执行循环体。
				if(f.isFile()) {
					size += f.length();
				}else if(f.isDirectory()) {
					size += fileSize(f);//Recursion
				}
			}
		}
		return size;
	}
	
	//需求2： 分层打印每个子目录及其大小
	public long layerFileSize(File dir, int layer) {
		long size = 0;
		File[] files = dir.listFiles();
		if(files != null) {
		//	Arrays.sort(files, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));//目录排序
			for(File f : files) {
				if(f.isFile()) {
					size += f.length();
				}else if(f.isDirectory()) {
					size += layerFileSize(f, layer + 1);//Recursion
				}
			}
		}
		String indent = "  ".repeat(layer);
	    System.out.println( indent + dir.getName() + " : " + size + " byte");
	    return size;
	}
	
	//需求 3：查找指定扩展名的文件（例如 .java）
	public long findFiles(File dir, String ext) {
	    long cnt = 0;
	    //if (!ext.startsWith(".")) ext = "." + ext;
	    File[] files = dir.listFiles();
	    if (files != null) {
	        for (File f : files) {
	            if (f.isFile()) {
	                if (f.getName().toLowerCase().endsWith(ext.toLowerCase())) {
	                    System.out.println(f.getAbsolutePath());
	                    cnt++;
	                }
	            } else if (f.isDirectory()) {
	                cnt += findFiles(f, ext);//Recursion
	            }
	        }
	    }
	    return cnt;
	}

	
	public static void main(String[] args) {
		File folder = new File("D:\\file1");
		FileTest ft = new FileTest();
		long totalSize = ft.fileSize(folder);
		System.out.println("Files total size is: " + totalSize + " byte");
		
		long layerSize = ft.layerFileSize(folder, 0);
		ft.findFiles(folder, ".class");
	}
	
}
