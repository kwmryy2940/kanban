# Gradle用のDockerイメージをベースイメージとして使用
FROM gradle:7.5.1-jdk17 AS build

# アプリケーションソースコードをコンテナにコピー
COPY --chown=gradle:gradle . /home/gradle/src

# Gradleでビルド
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# 実行用のイメージを作成
FROM openjdk:17-alpine

# ビルド済みのJARファイルをコピー
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

# ポートを公開
EXPOSE 8080

# アプリケーションの実行
ENTRYPOINT ["java", "-jar", "/app.jar"]