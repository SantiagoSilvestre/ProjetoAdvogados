package conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class Conexao {
    String sql = "select * from Clientes";
    final private String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    final private String usuario = "";
    final private String senha = "";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    
    //String url = "jdbc:ucanaccess://C:/Users/Santiago/Documents/NetBeansProjects/ProjetoAdvogados/processo.accdb";
    String url = "jdbc:ucanaccess://././processo.accdb";
    
    public boolean conecta() {
        boolean result = true;
        
        try
        {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Banco de Dados Conectado!");
        }
        catch (ClassNotFoundException Driver)
        {
            JOptionPane.showMessageDialog(null, "Drive não localizado: "+Driver);
            result = false;
        }
        catch (SQLException Fonte)
        {
            JOptionPane.showMessageDialog(null, "Erro na Conexão com a fonte"
                    + " de dados" +Fonte);
            result = false;
        }
        return (result);
    }
    
    public void desconecta(){  
        boolean result = false;
        
        try 
        {
            conexao.close();
            JOptionPane.showMessageDialog(null, "Banco de dados fechou. Desconectou");
        }
        catch (SQLException erroSQL)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar o "
                    + "banco de dados" + erroSQL.getMessage());
            result = false;
        }
    }
   
    public Boolean executa(String sql) {
        try 
        {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
            return false;
        }
        catch (SQLException sqlex)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível executar"
                    + " a busca, verifique os valores informados");
        }
        return true;
    }
}
