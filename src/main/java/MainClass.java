import util.CheckUtil;
import vo.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("탐색할 드라이브의 번호를 입력해주세요");

        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            System.out.println((i + 1) + ". :" + roots[i]);
        }

        Scanner scanner = new Scanner(System.in);
        int index;
        while (true) {
            try {
                index = scanner.nextInt();
                if (index > 0 && index <= roots.length) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("부적절한 번호를 입력하셨습니다.");
            }
        }

        List<FileInfo> fileInfoList = new ArrayList<>();
        File root = new File("/Users/chooco13/Desktop");
        CheckUtil.init(root.getAbsolutePath());
        //        CheckUtil.check(roots[index - 1], fileInfoSet);
        CheckUtil.check(root, fileInfoList);
    }
}
