package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.seguranca;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Funcionario;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.FuncionarioRepositorio;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private FuncionarioRepositorio funcioRepo;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Funcionario funcionario = funcioRepo.findByUsername(username);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(funcionario.getRole()));
		
		User userSpring = new User(funcionario.getUsername(), funcionario.getSenha(), authorities);
		
		return userSpring;
	}
}
