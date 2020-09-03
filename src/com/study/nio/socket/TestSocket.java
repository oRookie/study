package com.study.nio.socket;

import java.io.IOException;

public class TestSocket {
    public static void main(String[] args) throws InterruptedException, IOException {
       Client client = new Client();
       client.connect("127.0.0.1",8888);
    }

}
