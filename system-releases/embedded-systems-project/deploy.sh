#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Script requires one argument - machine type"
    machine=jetson-xavier-nx-devkit-emmc
else
    machine=$1
fi

# Set your image type here
image=face-recognition-demo
deployfile=${image}-${machine}.tegraflash.tar.gz
scriptdir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
tmpdir=`mktemp`
rm -rf $tmpdir
mkdir -p $tmpdir
echo "Using temp directory $tmpdir"
pushd $tmpdir
cp $scriptdir/build/tmp/deploy/images/${machine}/$deployfile .
tar -xvf $deployfile
set -e
./doflash.sh
popd
echo "Removing temp directory $tmpdir"
rm -rf $tmpdir
