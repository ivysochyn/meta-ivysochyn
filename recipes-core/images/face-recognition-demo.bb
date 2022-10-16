inherit antmicro-jetson-base-image

IMAGE_INSTALL += " \
    python3-face-recognition-demo \
    face-recognition-demo-service \
    sqlite3 \
"
