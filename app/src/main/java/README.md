# Nossa Rua

Um aplicativo Android desenvolvido com Jetpack Compose que integra Google Maps e Google Places para exibir pontos e informações geográficas de interesse. Este README descreve as funcionalidades principais, dependências, instruções detalhadas de configuração (incluindo onde colocar a chave da API do Google Maps) e como rodar o app localmente.

> Atenção: a chave da API do Google Maps NÃO está pública neste repositório. Trata-se de uma API paga — não a adicione em arquivos versionados. As instruções abaixo mostram métodos seguros para configurá‑la localmente.

---

## Sumário

- [Funcionalidades](#funcionalidades)
- [Tecnologias e dependências principais](#tecnologias-e-depend%C3%AAncias-principais)
- [Pré-requisitos](#pr%C3%A9-requisitos)
- [Configuração da chave da API do Google Maps (não pública)](#configura%C3%A7%C3%A3o-da-chave-da-api-do-google-maps-n%C3%A3o-p%C3%BAblica)
- [Como rodar o app (desenvolvimento)](#como-rodar-o-app-desenvolvimento)
- [Build de release](#build-de-release)
- [Testes](#testes)
- [Boas práticas e segurança](#boas-pr%C3%A1ticas-e-seguran%C3%A7a)
- [Contribuição](#contribui%C3%A7%C3%A3o)
- [Contato](#contato)

---

## Funcionalidades

Observação: a lista abaixo descreve as funcionalidades implementadas/Kits utilizados no projeto. Se encontrar alguma discrepância, favor atualizar o README conforme a implementação atual.

- Exibição de mapa interativo (Google Maps) com marcadores e camadas.
- Integração com Google Places para busca/autocomplete de locais.
- Telas em Jetpack Compose (UI moderna declarativa).
- Navegação entre telas com Navigation Compose.
- Injeção de dependência com Hilt.
- Carregamento de imagens com Coil.
- Chamadas de rede com Retrofit (para APIs externas se aplicável).
- Paginação com Paging (se houver listas de conteúdo que suportam paginação).
- Suporte a testes instrumentados e unitários (dependências de teste adicionadas).

---

## Tecnologias e dependências principais

- Kotlin + Jetpack Compose
- Android Gradle Plugin (AGP)
- Google Play Services: Maps & Places
- Hilt (Injeção de dependência)
- Retrofit (HTTP client)
- Coil (image loading)
- Paging (quando aplicável)
- Secrets Gradle Plugin (opcional/recomendado para chaves sensíveis)

As versões exatas estão definidas em `gradle/libs.versions.toml` do projeto (uso de Version Catalog).

---

## Pré-requisitos

- Android Studio (recomendado) — versão compatível com AGP usado no projeto.
- JDK 17+ (o projeto configura JBR/JDK 21 no IntelliJ/Android Studio; ver `Project SDK` se necessário).
- Gradle (use o wrapper incluído: `./gradlew`).
- Dispositivo físico ou emulador com Google Play Services para testar mapas.

---

## Configuração da chave da API do Google Maps (não pública)

A aplicação usa a API do Google Maps/Places, que requer uma chave (API key). Esta chave é uma credencial paga e NÃO deve ser comitada no repositório.

Opções seguras para configurar a chave localmente:

1. Usando local.properties (método simples, não versionar)
    - Edite o arquivo `local.properties` na raiz do projeto (este arquivo já está no `.gitignore` por padrão).
    - Adicione:
      ```
      MAPS_API_KEY=YOUR_GOOGLE_MAPS_API_KEY
      ```
    - No módulo `app` (arquivo `app/build.gradle.kts`), recupere o valor e passe como `manifestPlaceholders` ou `buildConfigField`. Exemplo (Kotlin DSL):
      ```kotlin
      val mapsApiKey: String? = project.findProperty("MAPS_API_KEY") as String?
      android {
          defaultConfig {
              manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
          }
      }
      ```
    - No `AndroidManifest.xml` (ou no arquivo de configuração de mapas), use o placeholder:
      ```xml
      <meta-data
          android:name="com.google.android.geo.API_KEY"
          android:value="${MAPS_API_KEY}" />
      ```

2. Usando o Secrets Gradle Plugin (recomendado para projetos que desejam estrutura mais segura)
    - O projeto já inclui o plugin Google Secrets no catálogo (`gradle/libs.versions.toml`), então você pode seguir a configuração do plugin:
        - Crie um arquivo `secrets.properties` ou defina nas configurações do plugin conforme a documentação oficial do plugin de segredos.
        - Adicione a chave como propriedade privada, por exemplo:
          ```
          MAPS_API_KEY=YOUR_GOOGLE_MAPS_API_KEY
          ```
        - Configure o Secrets Plugin conforme a documentação para expor a chave via `BuildConfig` ou `manifestPlaceholders`.
    - Vantagem: fornece integração com CI e evita colocar chaves em VCS.

3. Variáveis de ambiente / CI
    - Para builds em CI, configure a variável no ambiente de execução (por exemplo, GitHub Actions Secrets) e injete no momento do build sem expor no repositório.

Importante:
- Nunca comite a chave em arquivos versionados (`.gitignore` já contém `local.properties`).
- Restrinja a chave de API no console do Google (por origem de pacote Android, SHA-1, ou restrição de IP) para reduzir uso indevido.

---

## Como rodar o app (desenvolvimento)

Opção A — Android Studio (recomendado)
1. Abra o projeto no Android Studio.
2. Aguarde o download/sync do Gradle (use o Gradle wrapper incluído).
3. Configure a chave do Maps conforme a seção "Configuração da chave..." (ex.: `local.properties` com `MAPS_API_KEY`).
4. Conecte um dispositivo físico ou configure um emulador com Google Play Services.
5. Execute `Run` (Play) ou selecione a configuração `app` e execute.

Opção B — Linha de comando
1. Certifique-se de ter o JDK no PATH e permissões de execução no `gradlew`.
2. Defina a chave da API no `local.properties` (ou em outra forma segura).
3. Build debug:
    - Linux / macOS:
      ```
      ./gradlew assembleDebug
      ./gradlew installDebug
      ```
    - Windows:
      ```
      gradlew.bat assembleDebug
      gradlew.bat installDebug
      ```
4. Inicie o app no dispositivo/emulador.

---

## Build de release

1. Configure sua chave do Maps para o build de release (mesma forma segura).
2. Crie e configure um keystore para assinar o APK/AAB (não comite o keystore nem a senha).
3. Gerar AAB/APK:
   ```
   ./gradlew bundleRelease   # gera .aab
   ./gradlew assembleRelease # gera .apk
   ```
4. Faça upload do AAB para a Google Play Console ou instale o APK assinado em dispositivos.

---

## Testes

- Unit tests: execute `./gradlew test`.
- Instrumented tests (Android): execute `./gradlew connectedAndroidTest` com um dispositivo/emulador ligado.

Observação: testes que dependem do Maps/Places podem requerer chaves válidas e/ou mocks. Recomenda‑se isolar chamadas de rede e usar testes instrumentados apenas quando necessário.

---

## Boas práticas e segurança

- Nunca adicione chaves de API a arquivos rastreados pelo Git.
- Use restrições de API no console do Google (p.ex. restrição por pacote Android e SHA-1).
- Para integração contínua (CI), use os mecanismos de secrets do provedor (GitHub Actions Secrets, GitLab CI variables, etc.) e injete durante o build.
- Revogue/regenerar chaves caso exista suspeita de vazamento.

---

## Contribuição

Contribuições são bem-vindas. Antes de abrir um PR, por favor:
- Abra uma issue descrevendo a proposta ou bug.
- Garanta que mudanças que toquem em chaves/credenciais mantenham essas informações fora do repositório.
- Siga as convenções do projeto (Kotlin + Compose).

---

## Contato

Se precisar de ajuda para configurar a chave do Maps ou rodar o projeto, abra uma issue neste repositório ou entre em contato com o mantenedor.

---

Obrigado por usar/avaliar o Nossa Rua! Se desejar, posso adaptar este README com:
- Capturas de tela reais do app,
- Comandos específicos do `app/build.gradle.kts` para injetar o MAPS_API_KEY (baseado no seu script de build),
- Exemplos de workflow de CI (GitHub Actions) que injetem a chave de forma segura.
