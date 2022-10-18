inherit antmicro-jetson-base-image

IMAGE_INSTALL += " \
    face-recognition-put \
    face-recognition-demo-service \
    sqlite3 \
"
