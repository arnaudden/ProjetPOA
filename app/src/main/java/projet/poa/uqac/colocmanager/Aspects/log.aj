package projet.poa.uqac.colocmanager.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import projet.poa.uqac.colocmanager.MainActivity;

public aspect Log {

    public pointcut callAddUser() : execution(void MainActivity.onClickAddUser(..));

    before () : callAddUser()
    {
        System.out.println(" aspect Clique sur addUser");
    }


}