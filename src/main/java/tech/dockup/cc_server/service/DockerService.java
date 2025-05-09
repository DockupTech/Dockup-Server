package tech.dockup.cc_server.service;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.transport.SSLConfig;
import com.github.dockerjava.zerodep.ZerodepDockerHttpClient;

@Service
public class DockerService {
  private DockerClient client;

  public DockerService() {
      DockerClientConfig dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
        .withDockerHost("tcp://localhost:2375")
        .withDockerTlsVerify(false)
        .build();

      ZerodepDockerHttpClient httpClient = new ZerodepDockerHttpClient.Builder()
        .dockerHost(URI.create("tcp://localhost:2375"))
        .build();

      client = DockerClientBuilder.getInstance(dockerClientConfig)
        .withDockerHttpClient(httpClient)
        .build();
  }

  public void getAllContainers(){
    List<Container> containers = client.listContainersCmd().exec();
    for (Container container : containers) {
      System.out.println(container.getNames()[0]);
    }
  }
    
}
