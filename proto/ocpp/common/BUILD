package(default_visibility = ["//visibility:public"])

#########################################################################
# Librerias Protobuf
#########################################################################

proto_library(
    name = "domain_proto",
    srcs = ["domain.proto"],
    deps = [
        "@com_google_protobuf//:timestamp_proto",
    ],
)

#########################################################################
# Librerias java para mensajes Protobuf
#########################################################################

java_proto_library(
    name = "domain_java_proto",
    visibility = ["//visibility:public"],
    deps = [":domain_proto"],
)

