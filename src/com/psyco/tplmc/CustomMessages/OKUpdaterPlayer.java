package com.psyco.tplmc.CustomMessages;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.kalmanolah.extras.OKReader;

public class OKUpdaterPlayer{
	
	public static void update(String name, String ver, String checkloc, String dlloc, CommandSender sender, String prefix){
		
		try {
			
			String latestver = null;
			
			sender.sendMessage(ChatColor.RED + prefix + "Initiating auto-update...");
			
			latestver = OKReader.main(checkloc + "?id=" + name);
			
			if(latestver != null){
			
				String[] halve = latestver.split("\\&");
				
				String[] newver = halve[0].split("\\.");
				
				String[] oldver = ver.split("\\.");
				
				int g = 0;
				
				int h = 0;
				
			    for (String s: newver){
			    	
			    	g++;
			    	
			    }
			    
			    for (String r: oldver){
			    	
			    	h++;
			    	
			    }
			    
			    String isnew = null;
			    
			    if(g > h){
			    	
			    	isnew = "yes";
			    	
			    }else if((g == h) || (g < h)){
			    	
			    	int z = 0;
			    	
			    	while((z < (g - 1)) || (z == (g - 1)) && isnew == null){
			    		
			    		if(Integer.parseInt(newver[z]) > Integer.parseInt(oldver[z])){
			    			
			    			isnew = "yes";
			    			
			    		}
			    		
			    		if(Integer.parseInt(newver[z]) < Integer.parseInt(oldver[z])){
			    			
			    			isnew = "no";
			    			
			    		}
			    		
			    		z++;
			    		
			    	}
			    	
			    }
			    
			    if(isnew == null){
			    	
			    	isnew = "no";
			    	
			    }
				
				if(isnew.equals("yes")){
					
				sender.sendMessage(ChatColor.RED + prefix + "A new version of " + name + ", v" + halve[0] + " is available.");
					
					new File("plugins" + File.separator + name).mkdir();
					
				    new File("plugins" + File.separator + name + File.separator + "update").mkdir();
				    
				    File file = new File("plugins" + File.separator + name + File.separator + "update" + File.separator + name + "-" + halve[0] + "-" + halve[1]);
				    
				    if(!file.exists()){
					
						sender.sendMessage(prefix + "Starting download of " + name + " v" + halve[0] + "...");
					    
					    FileOutputStream fos;
							
					    URL url = new URL(dlloc + "?id=" + name + "&ver=" + halve[0] + "&mc=1");
					    
					    ReadableByteChannel rbc = Channels.newChannel(url.openStream());
					    
						fos = new FileOutputStream("plugins" + File.separator + name + File.separator + "update" + File.separator + name + "-" + halve[0] + "-" + halve[1]);
						
					    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
					    
					    fos.close();
					    
					    sender.sendMessage(ChatColor.RED + prefix + name + "-" + halve[0] + "-" + halve[1] + " downloaded to " + File.separator + "plugins" + File.separator + name + File.separator + "update" + File.separator + ".");
				
				    }else{
				    	
				    	sender.sendMessage(ChatColor.RED + prefix + "You already have the latest version of " + name + " in your /plugins/" + name + "/update/ folder.");
				    	
				    }
					
				}else{
					
					sender.sendMessage(ChatColor.RED + prefix + "You already have the latest version of " + name + ".");
					
				}
			
			}
			
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + prefix + "Error while checking for latest version.");
		}
		
		
	}

}
