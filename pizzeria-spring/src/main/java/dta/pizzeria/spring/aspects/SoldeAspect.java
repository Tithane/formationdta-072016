package dta.pizzeria.spring.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SoldeAspect {
	
	@Pointcut("execution(* dta.pizzeria.spring.service.ClientRepository.save(..))") //expression
	private void toutesLesMethodesTransfert() {
	}

}
