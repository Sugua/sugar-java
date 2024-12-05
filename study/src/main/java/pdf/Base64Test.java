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
        String str="JVBERi0xLjcKJeLjz9MKNSAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDgzNz4+c3RyZWFtCnicpZbBjtwgDIbveYo8AcWAsS1VPVRqe247LzC7kxyq7mF76evXTAgJ2YGsVI2iGZh8Nhj7N6/D58vw4SuMgONlHnxk43AE8N5YGC+3wY2XP4M1OP4dPsbJWbHo+eaF0DGGcCWaED3ip8uv4ctl+D68rhYpGSRCQ8kgBMMhGfyY3g5zuM0zXBGCf0uCS2iwMoIVZyIt3CQB/Q1a70ewBqhCSKZZ4AbxOdoWht4wVhTfXIwEc4vQ6Bgb6rWRtzhZ10SiNc5XyBpMmiES2+a2HIgJ7iGKwd5I5ubWHIkhVlRPlfwdtTZ4fVgfp8+ENF+bOAcTk+cgxskSUA4K6bm5a3PB3pNhqLzGCDNNmit6HuhsbKLq6Y5uHuP8bNE9tb0J1a6cn0lu85mrJbve7ydgNCCVq2QeNbfQz5zwJkphQTdvPU8IaDy3PbnYLBnUsr2juxObsXnAqAvD2tO+YrqeNBvx4AlRmrWpCyNqemKODwQkowgLunlS/Qmt18kGI7FWgaudkmQ1kajnU0sAPsl00Bo7ps+Pb1kMfVyEMokOjC8D21Xo7hO/h5+PKEcqHamkOCatehkik/FUJhqYTxsKzpDcPW2jxvuCJoTd4vK4DwFqufsdtU70MadqyHtsnTjBciQ2bA1NF/PeGQe7AJaJPiZgwp7K4y4U0Jq4g9ZxF0Ir6d+NKhN9TAtDeBeOdaKPRcdLzyv5tE70sZx1xVtJwy5GIRqMO2ydOAn+oVTyuA8d6mkrsANWK60sueBUQTH3O699Tlve8h3y72ZbSL1d8GgiIZJxbvd4Z+wbVFkP2QQcfjevC6Xnb6YkIrLQrHoO/HRtXk6chwXVcAlkpVWVddy+JqTD98eFpwVi3oDu201p3FPd8GbvnCKdUe5FnfR6xPRfK2DrU3oIgxHKFlxzy1G0XvwoyAZzO+I4aVxDmDS4ejNNZDEhRlSCbXrx8e75bs2jsfk2gROmK+Q7LD0sFhFNgF3W53Ff7kuHWMZ95iDuRe371EHbi9ifULW0F63vUwdlL1J/QlXKvip9n6mFfRX6PnOQ9aLzJ1St6kXm+9RB1IvKn1C1pheR71MHSS8afxL1StJf6iw+UfjdxeZtrv8DzloBRgplbmRzdHJlYW0KZW5kb2JqCjQgMCBvYmoKPDwvQ29udGVudHMgNSAwIFIvTWVkaWFCb3hbMCAwIDg0MiAxMTkwXS9QYXJlbnQgMiAwIFIvUmVzb3VyY2VzPDwvRm9udDw8L0YxIDYgMCBSPj4+Pi9UcmltQm94WzAgMCA4NDIgMTE5MF0vVHlwZS9QYWdlPj4KZW5kb2JqCjEgMCBvYmoKPDwvUGFnZXMgMiAwIFIvVHlwZS9DYXRhbG9nPj4KZW5kb2JqCjMgMCBvYmoKPDwvQ3JlYXRpb25EYXRlKEQ6MjAyNDEyMDQxNTUxNTkrMDgnMDAnKS9Nb2REYXRlKEQ6MjAyNDEyMDQxNTUxNTkrMDgnMDAnKS9Qcm9kdWNlcihpVGV4dK4gNy4xLjE1IKkyMDAwLTIwMjEgaVRleHQgR3JvdXAgTlYgXChBR1BMLXZlcnNpb25cKSk+PgplbmRvYmoKNyAwIG9iago8PC9Bc2NlbnQgODgwL0NhcEhlaWdodCA4ODAvRGVzY2VudCAtMTIwL0ZsYWdzIDYvRm9udEJCb3hbLTEzNCAtMjU0IDEwMDEgOTA1XS9Gb250TmFtZS9TVFNvbmdTdGQtTGlnaHQvSXRhbGljQW5nbGUgMC9TdGVtViA5My9TdHlsZTw8L1Bhbm9zZTwwMTA1MDAwMDA0MDAwMDAwMDAwMDAwMDA+Pj4vVHlwZS9Gb250RGVzY3JpcHRvcj4+CmVuZG9iago2IDAgb2JqCjw8L0Jhc2VGb250L1NUU29uZ1N0ZC1MaWdodC1VbmlHQi1VQ1MyLUgvRGVzY2VuZGFudEZvbnRzWzggMCBSXS9FbmNvZGluZy9VbmlHQi1VQ1MyLUgvU3VidHlwZS9UeXBlMC9UeXBlL0ZvbnQ+PgplbmRvYmoKMiAwIG9iago8PC9Db3VudCAxL0tpZHNbNCAwIFJdL1R5cGUvUGFnZXM+PgplbmRvYmoKOCAwIG9iago8PC9CYXNlRm9udC9TVFNvbmdTdGQtTGlnaHQvQ0lEU3lzdGVtSW5mbzw8L09yZGVyaW5nKEdCMSkvUmVnaXN0cnkoQWRvYmUpL1N1cHBsZW1lbnQgND4+L0RXIDEwMDAvRm9udERlc2NyaXB0b3IgNyAwIFIvU3VidHlwZS9DSURGb250VHlwZTAvVHlwZS9Gb250L1cgWzFbMjA3XTE1WzIzOF0xN1s0NjIgNDYyIDQ2MiA0NjIgNDYyIDQ2MiA0NjJdMjVbNDYyIDQ2Ml0zNVs1NjAgNjk1XTQxWzc5M100N1s3NThdXT4+CmVuZG9iagp4cmVmCjAgOQowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDEwNTQgMDAwMDAgbiAKMDAwMDAwMTU5MCAwMDAwMCBuIAowMDAwMDAxMDk5IDAwMDAwIG4gCjAwMDAwMDA5MTkgMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAxNDYzIDAwMDAwIG4gCjAwMDAwMDEyNTcgMDAwMDAgbiAKMDAwMDAwMTY0MSAwMDAwMCBuIAp0cmFpbGVyCjw8L0lEIFs8OWU0OWY0MTQ5NTgwZmY3MDFkN2Q3ZTYzODVjOTcwMGU+PDllNDlmNDE0OTU4MGZmNzAxZDdkN2U2Mzg1Yzk3MDBlPl0vSW5mbyAzIDAgUi9Sb290IDEgMCBSL1NpemUgOT4+CiVpVGV4dC03LjEuMTUKc3RhcnR4cmVmCjE4OTIKJSVFT0YK";
        log.info("base64Str={}", str);

        minBase64toFile(str);
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
