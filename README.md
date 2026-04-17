## 🌐 Language / Idioma

* 🇧🇷 [Português](#-português)
* 🇺🇸 [English](#-english)

---

# 🇧🇷 Português

# 🌳 Wild Invaders

**Wild Invaders** é um jogo arcade 2D inspirado em *Space Invaders*, onde o jogador controla um macaco que defende a floresta contra invasores humanos.

---

## 🎮 Conceito do Jogo

* **Tema:** Macaco vs Fazendeiros
* **Objetivo:** Eliminar todos os inimigos e sobreviver ao maior número de waves possível
* **Gênero:** Arcade 2D
* **Plataformas:** Desktop e Android

---

## 🕹️ Mecânicas Principais

* Movimentação lateral do jogador
* Disparo de projéteis (dinamites)
* Inimigos organizados em formação (grid)
* Progressão por waves
* Sistema de vidas
* Pontuação e high score

---

## ⚙️ Sistema de Progressão

A dificuldade do jogo é baseada na combinação de dois fatores:

### ⏱️ Progressão Global (Tempo)

* A cada 30 segundos:

  * A velocidade de movimento dos inimigos aumenta
  * A frequência de ataque aumenta

### 🧠 Progressão de Wave (Dinâmica)

* Baseada na quantidade de inimigos eliminados
* Quanto mais inimigos derrotados:

  * Maior a pressão sobre o jogador

### 🎯 Resultado

* A dificuldade cresce de forma contínua e dinâmica
* O jogo se adapta ao ritmo do jogador

---

## 👾 Tipos de Inimigos

* **Fazendeiros**

  * Ataque mais lento
  * Velocidade base menor

* **Garimpeiros**

  * Ataques mais rápidos
  * Maior nível de ameaça

---

## 🛡️ Elementos de Suporte

* **Troncos (barreiras)**

  * Bloqueiam projéteis
  * Podem ser destruídos
  * Criam decisões entre defesa e ataque

---

## 🧰 Especificações Técnicas

### ☕ Linguagem

* **Java 25 (Temurin LTS)**

### ⚙️ Build System

* **Gradle 9.4**

### 🤖 Android

* **Android Gradle Plugin:** 9.1.1
* **Compile SDK:** 35
* **Target SDK:** 35
* **Min SDK:** 21

### 🎮 Framework

* **LibGDX 1.14.0**

---

## 🧩 Arquitetura do Projeto

```
WildInvaders/
├── core/       # Lógica principal do jogo
├── desktop/    # Execução no desktop
├── android/    # Execução no Android
├── assets/     # Recursos do jogo (sprites, sons, fontes)
```

---

## 📦 Portabilidade

* ✔ Desktop (Windows/Linux)
* ✔ Android (APK)
* ⚠ Possível expansão futura para Web (HTML5)

---

# 🇺🇸 English

# 🌳 Wild Invaders

**Wild Invaders** is a 2D arcade game inspired by *Space Invaders*, where the player controls a monkey defending the forest from human invaders.

---

## 🎮 Game Concept

* **Theme:** Monkey vs Farmers
* **Objective:** Eliminate all enemies and survive as many waves as possible
* **Genre:** 2D Arcade
* **Platforms:** Desktop and Android

---

## 🕹️ Core Mechanics

* Player horizontal movement
* Projectile shooting (dynamite)
* Enemies organized in grid formation
* Wave-based progression
* Life system
* Score and high score

---

## ⚙️ Progression System

The game difficulty is based on two factors:

### ⏱️ Global Progression (Time)

* Every 30 seconds:

  * Enemy movement speed increases
  * Attack frequency increases

### 🧠 Wave Progression (Dynamic)

* Based on enemies eliminated
* The more enemies defeated:

  * The higher the pressure

### 🎯 Result

* Continuous and dynamic difficulty increase
* Adapts to player performance

---

## 👾 Enemy Types

* **Farmers**

  * Slower attacks
  * Lower base speed

* **Miners**

  * Faster attacks
  * Higher threat level

---

## 🛡️ Support Elements

* **Logs (barriers)**

  * Block projectiles
  * Can be destroyed
  * Create strategic decisions

---

## 🧰 Technical Specifications

### ☕ Language

* **Java 25 (Temurin LTS)**

### ⚙️ Build System

* **Gradle 9.4**

### 🤖 Android

* **Android Gradle Plugin:** 9.1.1
* **Compile SDK:** 35
* **Target SDK:** 35
* **Min SDK:** 21

### 🎮 Framework

* **LibGDX 1.14.0**

---

## 🧩 Project Architecture

```
WildInvaders/
├── core/       # Game logic
├── desktop/    # Desktop launcher
├── android/    # Android launcher
├── assets/     # Game assets
```

---

## 📦 Portability

* ✔ Desktop (Windows/Linux)
* ✔ Android (APK)
* ⚠ Possible future Web (HTML5) support
