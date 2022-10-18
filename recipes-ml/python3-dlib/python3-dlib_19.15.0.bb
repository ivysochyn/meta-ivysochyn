SUMMARY = "Deep Learning algorithms library"
LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://dlib/LICENSE.txt;md5=2c7a3fa82e66676005cd4ee2608fd7d2;"

FILESEXTRAPATHS:prepend = "${THISDIR}/files:"
SRC_URI += " \
    git://github.com/davisking/dlib.git;protocol=https;branch=master \
    file://0001-Remove-python-bindings-building.patch \
    file://0001-Remove-test-building.patch \
"
SRCREV = "6097093ab329fcd19aed03a8fe67949f6971a65d"

inherit pkgconfig python3native cuda cmake

OECMAKE_SOURCEPATH = "${S}/tools/python"
DEPENDS += " \
    python3 \
    python3-pybind11-native \
    python3-setuptools-native \
    python3-wheel-native \
    cuda-toolkit \
    cuda-nvrtc \
    cudnn \
    lapack \
    chrpath-native \
"

RDEPENDS:${PN} += " \
    lapack \
    cuda-nvrtc \
    cudnn \
"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=RELEASE \
    -DBUILD_SHARED_LIBS=TRUE \
    -DDLIB_USE_CUDA=1 \
    -DUSE_AVX_INSTRUCTIONS=1 \
    -DDLIB_USE_LAPACK=1 \
"

STAGING_LOCALDIR = "${WORKDIR}/recipe-sysroot/usr/local"
OECMAKE_CXX_FLAGS:append = " -I ${STAGING_LOCALDIR}/cuda-${CUDA_VERSION}/include/"
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

export CUDA_CUDA_LIBRARY="${STAGING_LOCALDIR}/cuda-${CUDA_VERSION}/stubs/libcuda.so" 
export CUDA_CUDNN_LIBRARY="${STAGING_LIBDIR}/libcudnn.so"
export CUDA_CUDART_LIBRARY="${STAGING_LOCALDIR}/cuda-${CUDA_VERSION}/lib/libcudart.so"
export CUDA_CUBLAS_LIBRARY="${STAGING_LOCALDIR}/cuda-${CUDA_VERSION}/lib/libcublas.so" 
export CUDA_NVRTC_LIBRARY="${STAGING_LOCALDIR}/cuda-${CUDA_VERSION}/lib/libnvrtc.so" 
export CUDA_CUDA_INCLUDE_DIRS="${STAGING_LOCALDIR}/cuda-${CUDA_VERSION}/include/" 
export CUDA_CUDNN_INCLUDE_DIRS="${STAGING_INCDIR}"
export CUDA_TOOLKIT_ROOT_DIR="${STAGING_LOCALDIR}/cuda-${CUDA_VERSION}/" 

do_install() {
    cd ${S}
    
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    PYTHONPATH=${D}${PYTHON_SITEPACKAGES_DIR} \
    ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py install ${DISTUTILS_INSTALL_ARGS} || \
    bbfatal_log "'${PYTHON_PN} setup.py install ${DISTUTILS_INSTALL_ARGS}' execution failed."

    cp ${B}/*dlib*cpython*${BUILD_ARCH}*.so ${B}/_dlib_pybind11.cpython-310-${TARGET_ARCH}-${TARGET_OS}-gnu.so
    cp ${B}/*dlib*cpython*${TARGET_ARCH}*.so ${D}${PYTHON_SITEPACKAGES_DIR}/
    cd ${D}${PYTHON_SITEPACKAGES_DIR}
    find . -type f -name "*.so" -exec chrpath -d "{}" \;
    cd -
}
