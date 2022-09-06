load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

def ocpp_server_git_repo():
    git_repository(
        name = "io_grpc_grpc_java",
        remote = "https://github.com/grpc/grpc-java.git",
        tag = "v1.41.0",
        verbose = False,
    )

def ocpp_server_deps(IO_GRPC_GRPC_JAVA_ARTIFACTS, IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS):
    maven_install(
        artifacts = IO_GRPC_GRPC_JAVA_ARTIFACTS + [
            "com.google.inject:guice:5.0.1",
            "eu.chargetime.ocpp:v1_6:1.0.1",
            "eu.chargetime.ocpp:common:1.0",
            "javax.xml.bind:jaxb-api:2.1",
            "javax.xml.soap:javax.xml.soap-api:1.4.0",
            "com.google.code.gson:gson:2.8.0",
            "org.java-websocket:Java-WebSocket:1.5.1",
            "org.slf4j:slf4j-api:2.0.0-alpha1",
            "org.slf4j:slf4j-jdk14:2.0.0-alpha1",
            "com.moandjiezana.toml:toml4j:0.7.2",
        ],
        generate_compat_repositories = True,
        override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
        repositories = [
            "https://repo.maven.apache.org/maven2/",
            "https://mvnrepository.com/",
        ],
    )
