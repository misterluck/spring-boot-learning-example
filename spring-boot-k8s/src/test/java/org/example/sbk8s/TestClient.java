package org.example.sbk8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.IOException;

@RunWith(SpringRunner.class)
public class TestClient {

    @Test
    public void test() throws ApiException, IOException {
        //直接写config path
        String kubeConfigPath = "config.yaml";

        //加载k8s, config
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

        //将加载config的client设置为默认的client
        Configuration.setDefaultApiClient(client);

        //创建一个api
        CoreV1Api api = new CoreV1Api();

        //打印所有的pod
        V1PodList list = api.listPodForAllNamespaces(null,null,null,null,null,null,null,
                null,null);

        for (V1Pod item : list.getItems()) {
            System.out.println(item);
        }
    }

}
