<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Livros</title>
</head>
<body>
<h1>Lista de Livros</h1>
<table border="1">
    <tr>
        <th>ISBN</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Quantidade</th>
    </tr>
    <%
        List<model.Livro> livros = (List<model.Livro>) request.getAttribute("livros");
        if (livros != null) {
            for (model.Livro livro : livros) {
    %>
    <tr>
        <td><%= livro.getIsbn() %></td>
        <td><%= livro.getNome() %></td>
        <td><%= livro.getCategoria() %></td>
        <td><%= livro.getQuantidade() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">Nenhum livro encontrado</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
