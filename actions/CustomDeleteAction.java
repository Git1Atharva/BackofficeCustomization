package org.training.backoffice.actions;


import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import de.hybris.platform.servicelayer.model.ModelService;
import org.training.core.model.PostsModel;

import javax.annotation.Resource;

public class CustomCapitalAction implements CockpitAction<Object, Object>
{

    @Resource
    private ModelService modelService;

    @Override
    public ActionResult<Object> perform(final ActionContext<Object> ctx)
    {
        ActionResult<Object> result = null;
        final Object data = ctx.getData();
        PostsModel postsModel = (PostsModel) data;
        String name = postsModel.getTitle();
        postsModel.setTitle(name.toUpperCase());
        System.out.print(postsModel.getTitle());
        modelService.save(postsModel);




        if (data != null)
        {
            result = new ActionResult<Object>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[] { data }));
        }
        else
        {
            result = new ActionResult<Object>(ActionResult.ERROR);
        }


        return result;
    }

    @Override
    public boolean canPerform(final ActionContext<Object> ctx)
    {
        final Object data = ctx.getData();
        return data != null;
    }

    @Override
    public boolean needsConfirmation(final ActionContext<Object> ctx)
    {
        return true;
    }

    @Override
    public String getConfirmationMessage(final ActionContext<Object> ctx)
    {
        return ctx.getLabel("confirmation.message");
    }
}