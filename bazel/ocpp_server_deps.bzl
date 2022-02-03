load("@rules_jvm_external//:defs.bzl", "maven_install")

def ocpp_server_deps():
    maven_install(
        artifacts = [
            "com.google.inject:guice:5.0.1",
            #"eu.chargetime.ocpp:v1_6:1.0.1",
            #"eu.chargetime.ocpp:common:1.0",
            "javax.xml.bind:jaxb-api:2.1",
            "javax.xml.soap:javax.xml.soap-api:1.4.0",
            "com.google.code.gson:gson:2.8.0",
            "org.java-websocket:Java-WebSocket:1.5.1",
            "org.slf4j:slf4j-api:2.0.0-alpha1",
            "org.slf4j:slf4j-jdk14:2.0.0-alpha1",
        ],
        generate_compat_repositories = True,
        repositories = [
            "https://repo.maven.apache.org/maven2/",
            "https://mvnrepository.com/",
        ],
    )
