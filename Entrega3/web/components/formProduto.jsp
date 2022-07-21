<%-- 
    Document   : formProduto
    Created on : 31/03/2021, 15:01:35
    Author     : joaop
--%>

<%@page import="models.produto.Produto"%>
<%@page import="models.categoria.CategoriaProduto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Produto novoProduto = (Produto)request.getAttribute("produto");
    
    ArrayList<CategoriaProduto> todasCategorias = (ArrayList<CategoriaProduto>)request.getAttribute("todasCategorias");
%>
<!-- FORM PRODUTO -->
<div class="rounded border border-dark p-4 m-5">
    <form method="POST" action="ProdutoController">                   
        <!-- ID (hidden) -->
        <input type="hidden" class="form-control" name="id" id="id_novo_produto" value="<%= novoProduto.getId() %>">
        <!-- PRODUTO -->
        <div class="form-group">
            <label for="nome_produto"><b>Produto</b></label>
            <input type="text" class="form-control" name="nome_produto" id="nome_produto" autocomplete="off" value="<%= novoProduto.getNome() %>" placeholder="Digite o Nome do Produto" required>
        </div>
        <!-- DESCRIÇÃO -->
        <div class="form-group">
            <label for="descricao"><b>Descrição</b></label>
            <input type="text" class="form-control" name="descricao" id="descricao" autocomplete="off" value="<%= novoProduto.getDescricao() %>" placeholder="Digite a Descrição do Produto" required>
        </div>
        <!-- PREÇO DE COMPRA -->
        <div class="form-group">
            <label for="preco_compra"><b>Preço de Compra</b></label>
            <input type="text" class="form-control" name="preco_compra" id="preco_compra" autocomplete="off" value="<%= novoProduto.getPrecoCompra() %>" placeholder="Digite o Preço de Compra do Produto" required>
        </div>
        <!-- PREÇO DE VENDA -->
        <div class="form-group">
            <label for="preco_venda"><b>Preço de Venda</b></label>
            <input type="text" class="form-control" name="preco_venda" id="preco_venda" autocomplete="off" value="<%= novoProduto.getPrecoVenda() %>" placeholder="Digite o Preço de Venda do Produto" required>
        </div>
        <!-- QUANTIDADE -->
        <div class="form-group">
            <label for="quantidade_disponivel"><b>Quantidade</b></label>
            <input type="text" class="form-control" name="quantidade_disponivel" id="quantidade_disponivel" autocomplete="off" value="<%= novoProduto.getQuantidade() %>" placeholder="Digite a Quantidade do Produto" required>
        </div>
        <!-- STATUS [LIBERADO | NÃO LIBERADO] -->
        <div class="form-group">
            <label for="liberado_venda"><b>Status</b></label>
            <select class="form-control" id="liberado_venda" name="liberado_venda" required>
                <option id=""></option>
                <option value="S" <%= novoProduto.getLiberado().equals("S") ? "selected" : "" %>>Liberado</option>
                <option value="N" <%= novoProduto.getLiberado().equals("N") ? "selected" : "" %>>Bloqueado</option>
            </select>
        </div>
        <!-- CATEGORIA -->
        <div class="form-group">
            <label for="id_categoria"><b>Categoria</b></label>
            <select class="form-control" id="id_categoria" name="id_categoria" required>
                <option id=""></option>
                <%
                    // Iterando pela lista de todas as Categorias
                    for (int index = 0; index < todasCategorias.size(); index++) {

                        // Pegando uma Categoria da lista de todas categorias
                        CategoriaProduto umaCategoria = todasCategorias.get(index);
                %>
                    <option value="<%= umaCategoria.getId() %>" <%= umaCategoria.getId() == novoProduto.getIdCategoria() ? "selected" : "" %>><%= umaCategoria.getCategoria() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Salvar</button>
    </form>
</div>
