package projet.poa.uqac.colocmanager.Aspects;

import android.support.v7.app.AppCompatActivity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import projet.poa.uqac.colocmanager.Activities.UtilisateurActivity;
import projet.poa.uqac.colocmanager.Activities.FactureActivity;

public aspect Test {

    public pointcut callAddUser() : execution(void MainActivity.onClickAddUser(..));
    public pointcut callOnCreate() : execution(void *.onCreate(..));

    before () : callAddUser()
    {
        System.out.println(" aspect test Clique sur addUser");
    }


}