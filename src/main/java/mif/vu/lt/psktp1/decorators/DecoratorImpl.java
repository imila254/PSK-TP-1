package mif.vu.lt.psktp1.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class DecoratorImpl implements ArtistDecorator{

    @Inject @Delegate @Any ArtistDecorator artistDecorator;

    public Integer DecoratedInt(Integer integer){
        System.out.println("Decorator is used");
        return artistDecorator.DecoratedInt(integer) * 10;
    }

}
