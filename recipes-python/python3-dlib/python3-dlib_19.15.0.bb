SUMMARY = "Deep Learning algorithms library"
LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://dlib/LICENSE.txt;md5=2c7a3fa82e66676005cd4ee2608fd7d2;"

FILESEXTRAPATHS:prepend = "${THISDIR}/files:"
SRC_URI += " \
    git://github.com/davisking/dlib.git;protocol=https;branch=master \
    file://0001-Remove-python-bindings-building.patch \
"
SRCREV = "929c630b381d444bbf5d7aa622e3decc7785ddb2"

inherit pkgconfig python3native cmake

OECMAKE_SOURCEPATH = "${S}/tools/python"
DEPENDS += " \
    python3 \
    python3-pybind11-native \
    python3-setuptools-native \
    python3-wheel-native \
"
S = "${WORKDIR}/git"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=RELEASE \
    -DBUILD_SHARED_LIBS=TRUE \
"
OECMAKE_GENERATOR = "Unix Makefiles"

FILES:${PN} += " \
    ${libdir}/* \
"

INSANE_SKIP:${PN}:append = "already-stripped"

DISTUTILS_INSTALL_ARGS ?= " \
    --root=${D} \
    --prefix=${prefix} \
    --install-lib=${PYTHON_SITEPACKAGES_DIR} \
    --install-data=${datadir} \
    --skip-build \
"

do_install() {
    cd ${S}
    
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    PYTHONPATH=${D}${PYTHON_SITEPACKAGES_DIR} \
    ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py install ${DISTUTILS_INSTALL_ARGS} || \
    bbfatal_log "'${PYTHON_PN} setup.py install ${DISTUTILS_INSTALL_ARGS}' execution failed."

    cp ${B}/dlib.cpython*${BUILD_ARCH}*.so ${B}/dlib.cpython-310-${TARGET_ARCH}-${TARGET_OS}-gnu.so
    cp ${B}/dlib.cpython*${TARGET_ARCH}*.so ${D}${PYTHON_SITEPACKAGES_DIR}/
}
