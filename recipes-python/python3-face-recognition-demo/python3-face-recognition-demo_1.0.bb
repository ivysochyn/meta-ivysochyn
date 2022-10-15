SUMMARY = "Face recognition application"
DESCRIPTION = "An application to detect and recognize faces from predifined list of people (images)"
HOMEPAGE = "https://github.com/ivysochyn/embdedded-systems-project"

SRC_URI = "git://github.com/ivysochyn/embdedded-systems-project.git;protocol=https;branch=main"
SRCREV = "2c706eeb484df805f172144ec78486f2d5ed13d4"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d5db30463f0b9397b0ecb7f402a88a49"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

do_install() {
    install -d ${D}/home/root/face-recognition-demo/
    install ${S}/camera.py ${D}/home/root/face-recognition-demo/camera.py
    install ${S}/database.py ${D}/home/root/face-recognition-demo/database.py
}

FILES:${PN} += " \
    /home/root/face-recognition-demo/camera.py \
    /home/root/face-recognition-demo/database.py \
"

RDEPENDS:${PN} += " \
    python3-core \
    python3-face-recognition \
    python3-pysqlite3 \
    python3-opencv \
    python3-numpy \
"
