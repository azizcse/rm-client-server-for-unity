package com.example.user22.rmserver.controller;

/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 4/19/2018 at 12:52 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 4/19/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/


import android.content.Context;

import io.left.rightmesh.android.AndroidMeshManager;
import io.left.rightmesh.android.MeshService;
import io.left.rightmesh.id.MeshID;
import io.left.rightmesh.mesh.MeshManager;
import io.left.rightmesh.mesh.MeshStateListener;
import io.left.rightmesh.util.RightMeshException;
import io.reactivex.functions.Consumer;

public class RightMeshController implements MeshStateListener{
    private static final int MESH_PORT = 45623;

    // MeshManager instance - interface to the mesh network.
    private AndroidMeshManager meshManager = null;
    private static RightMeshController rightMeshController;

    private RightMeshController() {
    }

    public static RightMeshController on() {
        return rightMeshController = rightMeshController == null ? new RightMeshController() : rightMeshController;
    }

    /**
     * Get a {@link AndroidMeshManager} instance, starting RightMesh if it isn't already running.
     *
     * @param context service context to bind to
     */
    public void connect(Context context) {
        meshManager = AndroidMeshManager.getInstance(context, RightMeshController.this, "Test_net");
    }

    /**
     * Close the RightMesh connection, stopping the service if no other apps are running.
     */
    public void disconnect() {
        try {
            meshManager.stop();
        } catch (MeshService.ServiceDisconnectedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void meshStateChanged(MeshID meshID, int state) {
        if (state == MeshStateListener.SUCCESS) {

            try {
                // Binds this app to MESH_PORT.
                // This app will now receive all events generated on that port.
                meshManager.bind(MESH_PORT);
                // Subscribes handlers to receive events from the mesh.
                meshManager.on(MeshManager.DATA_RECEIVED, new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
                meshManager.on(MeshManager.PEER_CHANGED, new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });


            } catch (RightMeshException e) {
                e.printStackTrace();
            }
        }
    }

}
