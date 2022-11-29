FROM openjdk:11
COPY . /usr/src/sudoku
WORKDIR /usr/src/sudoku
# compile
RUN chmod a+x ./entrypoint.sh \
    ./entrypoint.sh build

ENTRYPOINT [ "sh", "./entrypoint.sh" ]