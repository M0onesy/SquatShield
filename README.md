# SquatShield

一个基于 Fabric 的 Minecraft 模组项目。

## 环境要求
- Java 21
- Gradle Wrapper（仓库已包含）
- Minecraft 1.21.6
- Fabric Loader 0.18.4

## 构建
Windows:
```powershell
.\gradlew.bat build
```

Linux/macOS:
```bash
./gradlew build
```

构建产物位于 `build/libs/`。

## 开发运行（客户端）
Windows:
```powershell
.\gradlew.bat runClient
```

## 项目结构
- `src/main/java`：源码
- `src/main/resources`：模组资源与 `fabric.mod.json`
- `.github/workflows/build.yml`：CI 构建流程

## License
CC0 1.0（见 `LICENSE`）。
