package FileUtil;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class FileUtils {


	/**
	 * 文字列パスをパスへ変換する。
	 * @param str 文字列パス
	 * @return パス
	 */
	public static Path convertStringPathToPath(String str) {
		return FileSystems.getDefault().getPath(str);
	}

	/**
	 * ディレクトリを作成する。
	 * @param path ディレクトリパス
	 * @return 作成ディレクトリ
	 * @throws IOException I/Oが発生した場合。
	 */
	public static Path createDirectory(Path path) throws IOException {
		return Files.createDirectories(path);
	}

	public static Path createFile (String strDirectoryWithFileName) {
		return Paths.get(strDirectoryWithFileName);
	}

	public static Path createFile(Path path,String fileName) {
		return createFile(joinDirectoryAndFileName(path.toString(),fileName));
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
			deleteDirectory(path);
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

	private static String joinDirectoryAndFileName(String directory,String fileName) {
		return new String();
	}

}
