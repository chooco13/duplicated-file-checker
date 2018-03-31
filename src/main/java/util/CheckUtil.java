package util;

import vo.FileInfo;

import java.io.File;
import java.util.List;

public class CheckUtil {
    private static String DUPLICATED_PATH;

    public static void setDuplicatedPath(String duplicatedPath) {
        DUPLICATED_PATH = duplicatedPath;
    }

    public static void init(String rootPath){
        DUPLICATED_PATH = rootPath + "/duplicated";
        new File(DUPLICATED_PATH).mkdir();
    }

    public static List<FileInfo> check(File file, List<FileInfo> fileInfoList) {
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File listFile : listFiles) {
                if (listFile.isFile()) {
                    FileInfo currentFile = new FileInfo(listFile.getAbsolutePath(), listFile.getName(), listFile.length());
                    if (fileInfoList.contains(currentFile)) {
                        System.out.println("이 파일은 중복된 파일으로 보입니다. duplicated 폴더로 이동합니다.");
                        FileInfo originalFile = fileInfoList.get(fileInfoList.indexOf(currentFile));
                        System.out.println("원본 경로:" + originalFile.getPath());
                        System.out.println("중복 경로:" + currentFile.getPath());

                        if (listFile.renameTo(new File(DUPLICATED_PATH + "/" + listFile.getName()))) {
                            System.out.println("File is moved successful!");
                        } else {
                            System.out.println("File is failed to move!");
                        }
                        System.out.println();
                    } else {
                        fileInfoList.add(currentFile);
                    }
                } else if (listFile.isDirectory()) {
                    if (!listFile.getAbsolutePath().equals(DUPLICATED_PATH)) {
                        check(listFile, fileInfoList);
                    }
                }
            }
        }

        return fileInfoList;
    }
}
