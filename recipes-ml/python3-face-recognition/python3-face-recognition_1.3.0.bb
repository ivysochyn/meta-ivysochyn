SUMMARY = "Face Recognition"
DESCRIPTION = "Recognize and manipulate faces from Python or from the command line with the world’s simplest face recognition library."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5350ad154eb80290f2faad56592be730;"

SRC_URI[sha256sum] = "5e5efdd1686aa566af0d3cc1313b131e4b197657a8ffd03669e6d3fad92705ec"
SRC_URI[md5sum] = "4e54f245f8fe4751a9f0ef5301a7cd40"

inherit pypi setuptools3

PYPI_PACKAGE = "face_recognition"

RDEPENDS:${PN} += " \
    dlib \
    python3-dlib \
    python3-core \
    python3-numpy \
    python3-pillow \
    python3-click \
    python3-face-recognition-models \
"
