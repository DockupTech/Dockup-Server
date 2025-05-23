package tech.dockup.cc_server.service;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectVolumeResponse;
import com.github.dockerjava.api.command.ListVolumesResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.zerodep.ZerodepDockerHttpClient;

import io.vavr.control.Try;
import jakarta.annotation.PostConstruct;
import tech.dockup.cc_server.config.DockerConfig;

@Service
public class DockerService {

  private static final Logger logger = LoggerFactory.getLogger(DockerService.class);
  private DockerClient client;

  @Autowired
  private DockerConfig dockerConfig;

  public static class DockerConnectionException extends RuntimeException {
    public DockerConnectionException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  @PostConstruct
  public void connectToDockerAPI() throws Throwable {
    client = Try.<String>of(() -> new StringBuilder(dockerConfig.protocol)
        .append("://")
        .append(dockerConfig.host)
        .append(dockerConfig.port == "" ? "" : ":")
        .append(dockerConfig.port)
        .toString()
      )
      .map((String connectionString) -> DefaultDockerClientConfig.createDefaultConfigBuilder()
        .withDockerHost(connectionString)
        .withDockerTlsVerify(dockerConfig.tlsVerify)
        .build()
      )
      .map(clientConfig -> Pair.of(clientConfig, new ZerodepDockerHttpClient.Builder()
          .dockerHost(clientConfig.getDockerHost())
          .build())
      )
      .map(pair -> DockerClientBuilder.getInstance(pair.getLeft())
        .withDockerHttpClient(pair.getRight())
        .build()
      )
      .andThen(client -> client.listContainersCmd().exec())
      .andThen(_ -> logger.info("Successfully connected to Docker API"))
      .recoverWith(Exception.class, e -> Try.failure(new DockerConnectionException("Failed to build Docker client", e)))
      .getOrElseThrow(e -> e);

  }

  public DockerService() {}


  public void getAllContainers(){
    ListVolumesResponse response = client.listVolumesCmd().exec();
    List<InspectVolumeResponse> volumes = response.getVolumes();
    //System.out.println(volumes.get(0));


    List<Container> containers = client.listContainersCmd().exec();
    for (Container container : containers) {
      //System.out.println(container.getMounts().get(0));
    }
  }
}
