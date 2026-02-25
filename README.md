# SquatShield

SquatShield 是一个 Fabric 客户端模组，将盾牌格挡触发从右键改为潜行键（默认 Shift），以贴近基岩版常见操作习惯。

## 功能说明

- 右键不再触发盾牌格挡。
- 按下潜行键时自动尝试举盾。
- 同时持有多个盾牌时，优先使用副手，其次主手。
- 持续按住潜行键可保持举盾状态。

## 已验证环境

- Minecraft 1.21.6
- Fabric Loader 0.18.4
- Fabric API 0.128.2+1.21.6
- Java 21

## 安装（玩家）

1. 安装与游戏版本匹配的 Fabric Loader。
2. 确保 `fabric-api` 已安装到 `mods/`。
3. 将 `squatshield-<version>.jar` 放入游戏目录的 `mods/`。
4. 启动 Fabric 配置文件进入游戏。

## 使用说明

1. 在主手或副手携带盾牌。
2. 按潜行键（默认 Shift）触发格挡。
3. 若你修改了潜行键绑定，则以新的按键绑定为准。

## 开发与构建

### 构建

Windows:

```powershell
.\gradlew.bat build
```

Linux/macOS:

```bash
./gradlew build
```

构建产物位于 `build/libs/`。

### 本地运行客户端

Windows:

```powershell
.\gradlew.bat runClient
```

Linux/macOS:

```bash
./gradlew runClient
```

## 项目结构

- `src/main/java`：模组源码与 Mixin 逻辑。
- `src/main/resources`：`fabric.mod.json`、Mixin 配置与资源文件。
- `.github/workflows/build.yml`：GitHub Actions 持续集成构建流程。

## 已知限制与兼容提示

- 本模组属于客户端输入行为改写类模组。
- 若其他模组同时修改盾牌右键行为或输入事件处理逻辑，可能出现兼容冲突。

## License

本项目基于 `CC0-1.0` 发布，详见 `LICENSE`。
