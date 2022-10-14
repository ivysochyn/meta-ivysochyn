# meta-put
Layer introduces demo for jetson-xavier-nx with face recognition.

## How to build
In order to start building image initialize repo first:
```
mkdir build-demo; cd build-demo
repo init -u https://github.com/ivysochyn/meta-put.git -m system-releases/put-embedded-systems/manifest.xml -b main
repo sync -j`nproc`
```

Run the docker and start building image:
```
sudo docker run --rm -v $PWD:/data -u $(id -u):$(id -u) -it yoctobuilder``
PARALLEL_MAKE="-j $(nproc)" BB_NUMBER_THREADS="$(nproc)" MACHINE="jetson-xavier-nx-devkit-emmc" bitbake face-recognition-demo
```
