package com.elaina;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, Exception {
        String uploadFilePath = "H:/imgs/bb.jpg";
        String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
        //1.加载配置文件，配置文件中的内容就是tracker 服务的地址
        ClientGlobal.init(filePath);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer=null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        String[] strings = storageClient.upload_file(uploadFilePath, "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println("上传完成");
    }
}
