package br.edu.uniaeso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Funcionario {
    private String nome;
    private double salario;
    private Date dataAdmissao;

    public Funcionario(String nome, double salario, Date dataAdmissao) {
        this.nome = nome;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void aumentarSalario(double percentual) {
        this.salario *= (1 + percentual / 100);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", salario=" + salario +
                ", dataAdmissao=" + dataAdmissao +
                '}';
    }
}

package br.edu.uniaeso;

class Departamento {
    private String nome;
    private List<Funcionario> funcionarios;

    public Departamento(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public boolean adicionarFuncionario(Funcionario funcionario) {
        if (funcionarios.size() < 100) {
            return funcionarios.add(funcionario);
        } else {
            return false;
        }
    }

    public boolean removerFuncionario(Funcionario funcionario) {
        return funcionarios.remove(funcionario);
    }

    public void aumentarSalarioFuncionarios(double percentual) {
        for (Funcionario f : funcionarios) {
            f.aumentarSalario(percentual);
        }
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "nome='" + nome + '\'' +
                ", funcionarios=" + funcionarios +
                '}';
    }
}

package br.edu.uniaeso;

class Empresa {
    private String nome;
    private String cnpj;
    private List<Departamento> departamentos;

    public Empresa(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.departamentos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public boolean adicionarDepartamento(Departamento departamento) {
        if (departamentos.size() < 10) {
            return departamentos.add(departamento);
        } else {
            return false;
        }
    }

    public Departamento getDepartamentoPorNome(String nome) {
        for (Departamento d : departamentos) {
            if (d.getNome().equals(nome)) {
                return d;
            }
        }
        return null;
    }

    public boolean transferirFuncionario(String nomeFuncionario, String nomeDepartamentoOrigem, String nomeDepartamentoDestino) {
        Departamento origem = getDepartamentoPorNome(nomeDepartamentoOrigem);
        Departamento destino = getDepartamentoPorNome(nomeDepartamentoDestino);
        
        if (origem == null || destino == null) {
            return false;
        }
        
        for (Funcionario f : origem.getFuncionarios()) {
            if (f.getNome().equals(nomeFuncionario)) {
                origem.removerFuncionario(f);
                destino.adicionarFuncionario(f);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", departamentos=" + departamentos +
                '}';
    }
}

package br.edu.uniaeso;

public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa("Tech Solutions", "12345678000199");

        Departamento devDept = new Departamento("Desenvolvimento");
        Departamento hrDept = new Departamento("Recursos Humanos");
        empresa.adicionarDepartamento(devDept);
        empresa.adicionarDepartamento(hrDept);

        Funcionario f1 = new Funcionario("Alice", 5000.00, new Date());
        Funcionario f2 = new Funcionario("Bob", 4500.00, new Date());
        devDept.adicionarFuncionario(f1);
        devDept.adicionarFuncionario(f2);

        Funcionario f3 = new Funcionario("Charlie", 4000.00, new Date());
        hrDept.adicionarFuncionario(f3);

        devDept.aumentarSalarioFuncionarios(10);

        empresa.transferirFuncionario("Bob", "Desenvolvimento", "Recursos Humanos");

        System.out.println(empresa);
    }
}
