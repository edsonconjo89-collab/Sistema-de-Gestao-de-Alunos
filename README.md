# Sistema de Gestão de Alunos

Sistema completo para gerenciamento acadêmico com persistência de dados em Java.

## 🎯 Funcionalidades

### 📊 Gestão de Alunos
- **CRUD Completo**: Criar, ler, atualizar e remover alunos
- **Validação de Dados**: Verificação de matrícula única e dados válidos
- **Dual Storage**: Otimização com List e Map para acesso rápido

### 🔍 Buscas e Filtros
- **Busca por Matrícula**: Acesso direto via HashMap
- **Filtro por Curso**: Listagem de alunos por curso específico
- **Status Acadêmico**: Identificação de alunos aprovados
- **Ranking**: Ordenação por média decrescente

### 💾 Persistência
- **Salvamento Automático**: Dados persistidos automaticamente
- **Recuperação**: Carregamento automático ao iniciar
- **Serialização**: Dados salvos em formato binário

## 📁 Armazenamento de Dados

Os dados são automaticamente salvos nos arquivos:
- `listalunos.sga` e `mapalunos.sga` - Persistência dos registros

## 🎨 Interface

Interface de linha de comando (CLI) intuitiva com menu interativo:

```
=== SISTEMA DE GESTÃO DE ALUNOS ===
1. Adicionar novo aluno
2. Listar todos os alunos
3. Buscar aluno por matrícula
4. Atualizar aluno
5. Remover aluno
6. Listar alunos por curso
7. Mostrar alunos aprovados
8. Ranking por média
9. Sair
```

## 🔒 Tratamento de Erros

- Matrículas duplicadas
- Média fora do intervalo (0-20)
- Dados obrigatórios ausentes
- Erros de entrada/saída de arquivos
- Tratamento adequado de exceções

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## ✨ Recursos Futuros

- [ ] Interface gráfica (JavaFX/Swing)
- [ ] Exportação para PDF/Excel
- [ ] Autenticação de usuários
- [ ] Dashboard estatístico
- [ ] API REST
- [ ] Integração com banco de dados

---

**Desenvolvido com ❤️ usando Java puro**

*Sistema educacional para fins acadêmicos*
