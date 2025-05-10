package tech.dockup.cc_server.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.zerodep.ZerodepDockerHttpClient;

import jakarta.annotation.PostConstruct;
import tech.dockup.cc_server.config.DockerConfig;

@Service
public class DockerService {

  private DockerClient client;

  @Autowired
  private DockerConfig dockerConfig;

  @PostConstruct
  public void connectToDockerAPI() {
    String connectionString = new StringBuilder(dockerConfig.protocol)
      .append("://")
      .append(dockerConfig.host)
      .append(dockerConfig.port == "" ? "" : ":")
      .append(dockerConfig.port)
      .toString();
    
    DockerClientConfig dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
        .withDockerHost(connectionString)
        .withDockerTlsVerify(dockerConfig.tlsVerify)
        .build();

      ZerodepDockerHttpClient httpClient = new ZerodepDockerHttpClient.Builder()
        .dockerHost(URI.create(connectionString))
        .build();

      client = DockerClientBuilder.getInstance(dockerClientConfig)
        .withDockerHttpClient(httpClient)
        .build();
  }

  public DockerService() {}

  
  public void getAllContainers(){
    List<Container> containers = client.listContainersCmd().exec();
    for (Container container : containers) {
      System.out.println(container.getMounts().size());
    }
  }
    
}
