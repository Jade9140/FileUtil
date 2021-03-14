package FileUtil;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

public class FileUtils {

	public static boolean createDirectory(Path path) {

		return true;
	}

	public static boolean createFile(Path path,String fileName) {

		return true;
	}

	/**
	 * 指定したパスのファイルを削除する
	 * @param path 削除対象ファイルパス
	 * @return boolean 成功:true/失敗:false
	 * @throws IOException
	 */
	public static boolean deleteFile(Path path) throws IOException {

		return Files.deleteIfExists(path);
	}

	/**
	 * 指定したディレクトリを削除する。
	 * @param path
	 * @return
	 */
	public static boolean deleteDirectory(Path path) throws IOException {

		try{
			if(Files.isDirectory(path)) {
				Files.delete(path);
			}else {
				return false;
			}
		}catch(NoSuchFileException e) {
			throw e;
		}catch(DirectoryNotEmptyException e) {
			// directory has subDirectory or subFile
			throw e;
		}catch(IOException e) {
			//
			throw e;
		}
		return true;
	}

	public static void deleteDirectoryWithSubDirectoryAndFile(Path path) throws IOException {

		try(DirectoryStream<Path> ds = Files.newDirectoryStream(path)){
			for(Path subPath : ds) {
				if(Files.isDirectory(subPath)) {
					deleteDirectoryWithSubDirectoryAndFile(subPath);
				} else {
					deleteFile(subPath);
				}
			}
		}
	}


	/**
	 * パスがパスリスト内に存在するか確認する
	 * @param pathList Pathリスト
	 * @param path パス
	 * @return boolean
	 */
	public static boolean existPathFormPathList(List<Path> pathList, Path path) {

		return pathList.contains(path);
	}



	public static boolean exists() {

		return true;
	}

	public static long getFileSize() {

		return  0L;
	}

	public static String getLastModifiedDateFormatString() {

		return new String();
	}

	public static Date getLastModifiedDateFormatDate() {

		return new Date();
	}

}
