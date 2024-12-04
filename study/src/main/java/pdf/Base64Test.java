package pdf;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Slf4j
public class Base64Test {
    public static void main(String[] args) {
        String dest = "pdf/报账单[400008133]-陕西网讯科技[31011012]-费用告知单.pdf";
        String base64String = fileToBase64(dest);
        log.info("base64Str={}", base64String);

        minBase64toFile(base64String);
    }

    private static void minBase64toFile(String base64String) {
        // Base64 编码的字符串（请替换为你自己的 Base64 字符串）
//        String base64String = "your_base64_encoded_string_here"; // 请替换为实际的 Base64 字符串

        // 输出文件路径
        String outputPath = "pdf/file.pdf"; // 请替换为你的输出文件路径

        try {
            // 解码 Base64 字符串为字节数组
            byte[] fileContent = Base64.getDecoder().decode(base64String);

            // 将字节数组写入文件
            Files.write(Paths.get(outputPath), fileContent);

            System.out.println("File created successfully at: " + outputPath);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
//            System.err.println("Error decoding Base64 string: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();

//            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


    public static String fileToBase64(String dest) {
        try (FileInputStream fis = new FileInputStream(new File(dest))) {
            StringBuilder sb = new StringBuilder();

            byte[] buffer = new byte[1024 * 8];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {

//                byte[] actualChunk=new byte[bytesRead];
//                System.arraycopy(buffer,0,actualChunk,0,bytesRead);
//                sb.append(Base64.getEncoder().encodeToString(actualChunk));

                byte[] actualChunk = new byte[bytesRead];
                System.arraycopy(buffer, 0, actualChunk, 0, bytesRead);

                // 将数据块编码为 Base64 并追加到 StringBuilder 中
                sb.append(Base64.getEncoder().encodeToString(actualChunk));
            }
            log.debug("encode = {} ", sb.toString());
            return sb.toString();
        } catch (Exception e) {
            log.error("转码失败:", e);
            throw new RuntimeException("转码失败，请联系管理员");
        }
    }


    public static String minFileToBase64(String filePath) {
//        String filePath = "path/to/your/file.ext"; // 请替换为你的文件路径

        try {
            // 将文件读取为字节数组
            File file = new File(filePath);
            byte[] fileContent = Files.readAllBytes(file.toPath());

            // 使用 Base64 编码器将字节数组编码为 Base64 字符串
            String encodedString = Base64.getEncoder().encodeToString(fileContent);


            // 输出 Base64 编码后的字符串
//            System.out.println("Base64 Encoded string: " + encodedString);
            return encodedString;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
