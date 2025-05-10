package tech.dockup.cc_server.config;

import org.springframework.context.annotation.Configuration;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class DockerConfig {

    @Pattern(regexp = "^(https|tcp|unix)$", message = "Protocol must be one of: https, tcp, unix")
    @Value("${dockup.docker.protocol:unix}")
    public String protocol;

    @Value("${dockup.docker.host:/var/run/docker.sock}")
    public String host;

    @Value("${dockup.docker.port:}")
    public String port;

    @Value("${dockup.docker.tlsVerify:false}")
    public boolean tlsVerify;

    @Value("${dockup.docker.certPath:}")
    public String certPath;

    @Value("${dockup.docker.keyPath:}")
    public String keyPath;

    @Value("${dockup.docker.caPath:}")
    public String caPath;
}
