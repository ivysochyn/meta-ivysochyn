SUMMARY = "pysqlite3"
DESCRIPTION = "This library takes the SQLite module from Python 3 and packages it as a separately-installable module"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=74d266f5351cf71cdedbfad85e95baac;"

SRC_URI[sha256sum] = "0352864898aa406beb762f4a620594c950a9a4430caab679bce574065698c8ac"
SRC_URI[md5sum] = "a3214fe716a60dc79a37b9e711490ed4"

inherit pypi distutils3

DEPENDS += " \
    python3-setuptools-native \
"
PYPI_PACKAGE = "pysqlite3"
