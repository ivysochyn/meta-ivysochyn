SUMMARY = "A toolkit for making real world machine learning and data analysis applications"
HOMEPAGE = "http://dlib.net"
SECTION = "libs"

LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://dlib/LICENSE.txt;md5=2c7a3fa82e66676005cd4ee2608fd7d2"

SRC_URI = "git://github.com/davisking/dlib.git;protocol=https;branch=master"
SRCREV = "929c630b381d444bbf5d7aa622e3decc7785ddb2"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=RELEASE"

EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=TRUE"

BBCLASSEXTEND =+ "native nativesdk"
