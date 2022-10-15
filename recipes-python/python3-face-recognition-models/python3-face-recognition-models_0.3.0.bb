SUMMARY = "Face Recognition Models"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e12c025f33913f2167514339be62c3fd;"

SRC_URI[sha256sum] = "b79bd200a88c87c9a9d446c990ae71c5a626d1f3730174e6d570157ff1d896cf"
SRC_URI[md5sum] = "53f60e9165958b2bb8841d66dde3f02d"

PYPI_PACKAGE = "face_recognition_models"

inherit pypi setuptools3

RDEPENDS:${PN} = " \
    python3-pkg-resources \
"
