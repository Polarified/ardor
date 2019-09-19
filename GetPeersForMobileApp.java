package com.jelurida.ardor.client.api;

import java.util.*;
import nxt.addons.JO;
import nxt.http.callers.GetPeersCall;
import nxt.peer.Peer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;



/*
new Thread(() -> {
        JO peersOf1 = GetPeersCall.create().active("true").state("CONNECTED").service("API", "CORS").includePeerInfo(true).
        remote(peer1url).trustRemoteCertificate(true).call();
        System.out.println(peersOf1);
        });
*/

public abstract class GetPeersForMobileApp {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Specify remote node url");
            System.exit(0);
        }
        URL url = new URL(args[0]);
        getPeers(url);
    }

    private static List<JO> getPeers(URL url) throws MalformedURLException {
        JO peers = GetPeersCall.create().active("true").state("CONNECTED").service("API", "CORS").includePeerInfo(true).
            remote(url).trustRemoteCertificate(true).call();
        //System.out.println(peers.toJSONString());
        List<JO> peersList = peers.getJoList("peers");
        List<String> addressesList = peersList.stream().map(p -> p.getString("address")).collect(Collectors.toList());
        addressesList.forEach(System.out::println);
        System.out.println(addressesList.size());
        return peersList;
    }



    //need to figure out how to map from key to 2 things - possibly have it go to a JO that has one field for info:peer.info
    //the other is list:peersList
    static Map<Peer, JO> map = new ConcurrentHashMap<>();


    Iterator iterator = list.iterator();
      while(iterator.hasNext()) {
        System.out.println(iterator.next());
    }

    private static void recGetPeers(Peer first){
        JO value = new { firstInfo : , friends : };
        map.put(first, value);
        for(Peer p : map.get(first)["friends"]["peers"]){
            if(!map.containsKey(p)){
                new Thread(() -> {
                    System.out.println("A new thread was created");
                }
                recGetPeers(Peer p);
            }
        }
    }

    //how can i get info of a single peer
    //how to iterate over a List<JO>

