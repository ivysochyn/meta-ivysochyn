FILEEXTRAPATHS:prepend = "${THISDIR}/files:"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI += " \
    file://illia.jpg \
    file://vlad.jpg \
    file://yarik.jpg \
"

do_install() {
    install -d ${D}/home/root/face-recognition-demo/images
    install ${S}/../illia.jpg ${D}/home/root/face-recognition-demo/images/illia.jpg
    install ${S}/../vlad.jpg ${D}/home/root/face-recognition-demo/images/vlad.jpg
    install ${S}/../yarik.jpg ${D}/home/root/face-recognition-demo/images/yarik.jpg
}

FILES:${PN} += "/home/root/face-recognition-demo/images/*"
