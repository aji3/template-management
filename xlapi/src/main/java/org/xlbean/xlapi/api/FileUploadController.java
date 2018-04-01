package org.xlbean.xlapi.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadController {
    @RequestMapping(method = RequestMethod.POST)
    public String post(
            @RequestParam("upload_file") MultipartFile multipartFile,
            @RequestParam("filetype") String fileType) { // 認証ユーザー情報

        // ファイルが空の場合は異常終了
        if (multipartFile.isEmpty()) {
            // 異常終了時の処理
        }

        // ファイル種類から決まる値をセットする
        StringBuffer filePath = new StringBuffer("/uploadfile")
            .append(File.separator)
            .append(fileType); // ファイルパス

        try {
            // // アップロードファイルを置く
            // File uploadFile =
            // new File(uploadDir.getPath() + "/" + fileType);
            File file = new File("test.jpg");
            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(file));
            uploadFileStream.write(bytes);
            uploadFileStream.close();

            return "You successfully uploaded.";
        } catch (Exception e) {
            // 異常終了時の処理
        } catch (Throwable t) {
            // 異常終了時の処理
        }
        return null;
    }

}
