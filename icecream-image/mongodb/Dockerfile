FROM mongo:8.0.10

LABEL maintainer='dangthaisan@hotmail.com'
LABEL repackaged='true'

# Copy init script into the image
COPY ./init-mongo.sh /docker-entrypoint-initdb.d/
